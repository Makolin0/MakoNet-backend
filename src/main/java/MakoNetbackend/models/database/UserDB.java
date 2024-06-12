package MakoNetbackend.models.database;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDB implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Positive
    private long id;

    @Email
    private String username;

    @NotNull
    private String password;

    @Size(min = 4, max = 32)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @NotNull
    private int lootboxes;

    @OneToMany(mappedBy = "user")
    private List<LootboxesDB> LootboxesList;

    private boolean isActive;


    public UserDB(String username, String password, String nickname, Role role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.isActive = true;
        this.lootboxes = 0;
    }
    public UserDB(String username, String password, String nickname, Role role, boolean isActive) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.isActive = isActive;
        this.lootboxes = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
