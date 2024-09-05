package com.gestaotech.api.dto.SemesterUser.Default;

import lombok.Data;

import java.util.List;

@Data
public class Software {

    private List<List<String>> list= List.of(
            List.of("IMD0017", "IMD0018", "IMD0020", "IMD1001", "IMD1002", "IMD1003", "IMD1004"),
            List.of("IMD0024", "IMD0027", "IMD0028", "IMD0034", "IMD0012"),
            List.of("IMD0029", "IMD0030", "IMD0033", "IMD0038", "IMD0121"),
            List.of("DIM0501", "DIM0504", "DIM0508","IMD0036","IMD0039","IMD0040"),
            List.of("DIM0125","DIM0138","DIM0507","DIM0546","DIM0549","IMD0043"),
            List.of("DIM0124","DIM0505","DIM0547"),
            List.of("DIM0511","DIM0516","DIM0548","DIM0614")
    );


}
