package com.example.rent_db.service.impl;

import com.example.rent_db.exception.ApartmentException;
import com.example.rent_db.exception.TokenException;
import com.example.rent_db.model.dto.AuthDto;
import com.example.rent_db.model.dto.FullApartmentsInfo;
import com.example.rent_db.model.entity.ApartmentEntity;
import com.example.rent_db.model.entity.UserApplicationEntity;
import com.example.rent_db.repository.UserApplicationRepository;
import com.example.rent_db.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.example.rent_db.constant.RentDbConstant.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserApplicationRepository userApplicationEntityRepository;

    /**
     * метод регистрации пользователя
     * @param user
     * @return
     */
    @Override
    public String registerUser(UserApplicationEntity user) {
        log.info("AuthServiceImpl: ->registerUser");

        UserApplicationEntity userByNikName = userApplicationEntityRepository.findAllByNikName(user.getNikName());
        if (userByNikName != null) {
            log.error("AuthServiceImpl: registerUser: 'userByNikName' IllegalArgumentException");
            return NICKNAME_REGISTRATION_ERROR;
        }

        UserApplicationEntity userByLogin = userApplicationEntityRepository.findAllByLogin(user.getLogin());

        if (userByLogin != null) {
            log.error("AuthServiceImpl: registerUser: 'userByLogin' IllegalArgumentException");
            return LOGIN_REGISTRATION_ERROR;
        }
        user.setDateRegistration(LocalDateTime.now());
        user.setBookingCount(0);
        userApplicationEntityRepository.save(user);
        log.info("AuthServiceImpl: <-registerUser");
        return REGISTRATION_DONE;
    }

    /**
     * метод авторизации пользователя
     * @param user
     * @return
     */
    @Override
    public String authUser(AuthDto user) {
        log.info("AuthServiceImpl: ->authUser");

        UserApplicationEntity userApplication = userApplicationEntityRepository.findUserByRegistration(user.getLogin(), user.getPassword());
        if (isNull(userApplication)) {
            log.error("AuthServiceImpl: authUser: 'userByRegistration' IllegalArgumentException");
            return INVALID_PARAMS_ERROR;
        } else {
            String token = generateToken();
            userApplication.setToken(token);
            userApplicationEntityRepository.save(userApplication);
            log.info("AuthServiceImpl: <-authUser");
            return token;

        }
    }

    /**
     * генерация уникальных значений
     * @return
     */
    private String generateToken() {
        String uniqueToken = UUID.randomUUID().toString();
        return uniqueToken + "|" + LocalDateTime.now().plusDays(1L);
    }

    /**
     * метод проверки авторизации пользователя
     * @param token
     */
    @Override
    public void checkToken(String token) {
        log.info("AuthServiceImpl: ->checkToken");
        UserApplicationEntity userApplication = userApplicationEntityRepository.findUserApplicationEntitiesByToken(token)
                .orElseThrow(TokenException::new);
        log.info("AuthServiceImpl:<-checkToken");
    }


    /**
     * планировщик задач
     * каждый час удаляет token авторизированного пользователя
     */
    @Scheduled(fixedDelay = 60000)
    public void checkValidTokensApplication() {
        log.info("AuthServiceImpl: scheduledRun -> checkValidTokensApplication");
        List<UserApplicationEntity> userByActiveToken = userApplicationEntityRepository.findUserApplicationEntitiesByTokenIsEmpty();
        if (!userByActiveToken.isEmpty()) {
            for (UserApplicationEntity user : userByActiveToken) {
                String token = user.getToken();
                int index = token.indexOf("|") + 1;
                String onlyLocalDate = token.substring(index);
                LocalDateTime timeToken = LocalDateTime.parse(onlyLocalDate);
                if (timeToken.isBefore(LocalDateTime.now())) {
                    user.setToken(null);
                    userApplicationEntityRepository.save(user);
                    log.info("AuthServiceImpl:scheduledStop <- checkValidTokensApplication (token "
                            + user.getLogin()
                            + "expired and has been deleted");
                } else {
                    log.info("AuthServiceImpl:scheduledStop <- checkValidTokensApplication");
                }
            }
        }
        log.info("AuthServiceImpl:scheduledStop <- checkValidTokensApplication (no active token)");
    }
}
