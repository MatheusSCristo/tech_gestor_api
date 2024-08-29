package com.gestaotech.api.dto.SemesterUser.Default;

import lombok.Data;

import java.util.List;

@Data
public class Computacao {
    private List<List<String>> list= List.of(
            List.of("IMD0017", "IMD0018", "IMD0020", "IMD1001", "IMD1002", "IMD1003", "IMD1004"),
            List.of("IMD0024", "IMD0027", "IMD0028", "IMD0034", "IMD1012"),
            List.of("IMD0029", "IMD0030", "IMD0033", "IMD0038", "IMD0121"),
            List.of("IMD0120", "IMD0039", "IMD0040","MAT0309","MAT0312"),
            List.of("IMD0122","DIM0404","DIM0549","DIM0601","EST0323"),
            List.of("DIM0138","DIM0605","DIM0606","IMD0036","IMD0401"),
            List.of("DIM0124","DIM0437","DIM0438","IMD0610")
    );
}
