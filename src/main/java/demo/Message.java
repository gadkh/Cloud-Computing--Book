package demo;

import java.util.Date;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MESSAGES")
public class Message {
	private String message;
	private Long id;
	private Date date;
	private Level level;
	private Details details;
	private Set<Like> like;

	public Message() {
		this.level = Level.Regular;
	}

	public Message(String message) {
		this();
		this.message = message;
		this.date = new Date();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Enumerated(EnumType.STRING)
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Embedded
	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	@OneToMany(
			fetch=FetchType.EAGER // LAZY
//			cascade=CascadeType.ALL
			)
	public Set<Like> getLike() {
		return like;
	}

	public void setLike(Set<Like> like) {
		this.like = like;
	}
	
	public void addLike (Like like) {
		this.like.add(like);
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", id=" + id + ", date=" + date + ", level=" + level + ", details="
				+ details + "]";
	}

}
