package org.boot.study.springboot.config.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import org.boot.study.springboot.domain.user.Role;
import org.boot.study.springboot.domain.user.User;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes
            , String nameAttributeKey, String name
            , String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // of() : OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName
            , Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // User 엔티티 생성. OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때
    // 가입할 때의 기본 권한을 GUEST로 주기 위해 role 빌더값에는 Role.GUEST를 사용
    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.GUEST)
                .build();
    }
}