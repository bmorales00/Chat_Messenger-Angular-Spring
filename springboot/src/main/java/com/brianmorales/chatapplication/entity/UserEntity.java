package com.brianmorales.chatapplication.entity;
import com.brianmorales.chatapplication.model.UserModel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Lob
    private String imgUrl;
    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;

}
