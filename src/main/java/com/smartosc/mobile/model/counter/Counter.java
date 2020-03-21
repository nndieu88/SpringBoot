package com.smartosc.mobile.model.counter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Counter {
    int count;

    public int increment() {
        return count++;
    }
}
