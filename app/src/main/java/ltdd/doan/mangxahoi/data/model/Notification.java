package ltdd.doan.mangxahoi.data.model;

import java.time.Instant;
import java.util.Date;

import ltdd.doan.mangxahoi.data.Utils;

public class Notification {
    private Integer id;
    private String type;
    private Integer from;
    private Integer to;
    private String created_at;
    private boolean is_seen;

    public Notification() {
    }

    public Notification(Integer id, String type, Integer from, Integer to, String created_at, boolean is_seen) {
        this.id = id;
        this.type = type;
        this.from = from;
        this.to = to;
        this.created_at = created_at;
        this.is_seen = is_seen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isIs_seen() {
        return is_seen;
    }

    public void setIs_seen(boolean is_seen) {
        this.is_seen = is_seen;
    }

    public Notification gerEx(int user_id){
        return new Notification(Utils.random(),Utils.randomT(),Utils.random(),user_id, Date.from(Instant.now()).toString(),false);
    }
}
