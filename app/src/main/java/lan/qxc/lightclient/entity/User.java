package lan.qxc.lightclient.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private Long userid;
    private String phone;
    private String username;
    private String password;
    private Byte sex;
    private String icon;

    //1997-11-13
    private String birthday;

    private String introduce;
    private String location;
    private String hometown;
    private String job;

    private Byte is_deleted;
    private Byte is_locked;

}
