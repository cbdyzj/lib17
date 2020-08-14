package org.jianzhao.java.json;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AppleTree {

    @NonNull
    private String name;

    private List<Apple> apples;
}
