package com.hairdresser.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Day implements Comparable<Day> {

    @Id
    private String id;
    private int start;
    private int end;
    private List<Visit> visits = new ArrayList<>();

    @Override
    public int compareTo(@NotNull Day o) {
        return Integer.compare(getStart(), o.getStart());
    }
}
