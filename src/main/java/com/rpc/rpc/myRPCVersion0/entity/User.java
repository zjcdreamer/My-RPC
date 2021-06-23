package com.rpc.rpc.myRPCVersion0.entity;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer Id;
    private String name;
    private Boolean gender;
}
