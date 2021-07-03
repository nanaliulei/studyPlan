package cn.liuliu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: liulei
 * @Time: 2021/7/3 16:58
 * @Description
 */

@Data
@Entity
@Table
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 性别
    private String sex;
    // 生日
    private String birthday;
    // 手机号
    private String phone;
    // 姓名
    private String name;
    // 证件类型
    private String idType;
    // 证件号
    private String idNo;
    // 创建时间
    private String createTime;
    // 销户标识 true:销户 false:未销户
    private boolean isCanceled;
}