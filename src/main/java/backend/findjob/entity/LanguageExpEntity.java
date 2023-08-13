package backend.findjob.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "lang_exp")
public class LanguageExpEntity extends BaseEntity{
    @Column
    private String language;
    @Column
    private Boolean isFirstLanguage;

    @Column
    private String oral;
    @Column
    private String written;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getFirstLanguage() {
        return isFirstLanguage;
    }

    public void setFirstLanguage(Boolean firstLanguage) {
        isFirstLanguage = firstLanguage;
    }

    public String getOral() {
        return oral;
    }

    public void setOral(String oral) {
        this.oral = oral;
    }

    public String getWritten() {
        return written;
    }

    public void setWritten(String written) {
        this.written = written;
    }
}
