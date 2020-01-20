package ImageHoster.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="comments")
public class Comment {
@Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="id")
         int id;
        @Column(name="text")
        String text;
        @Column(name="createdDate")
        Date createdDate;
       @OneToOne(fetch = FetchType.EAGER)
       @JoinColumn(name = "user_id")
        User user;
       @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
               @JoinColumn(name="image_id")
          Image image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
