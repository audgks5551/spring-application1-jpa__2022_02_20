package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    // (mappedBy = "member") -> 여기서 값을 변경한다고 해서 변경되지않는다 (읽기 모드 돌입)
    // Order의 member필드에서 변경이 일어나야 그때 Member의 orders가 변경됨
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
