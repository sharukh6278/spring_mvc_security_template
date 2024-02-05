package org.spring.security.repository;


import org.spring.security.beans.JWTToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JWTTokenRepository extends JpaRepository<JWTToken, Long> {
    JWTToken findByEmailAndAccessToken(String email,String accessToken);
    void deleteByEmail(String email);

}
