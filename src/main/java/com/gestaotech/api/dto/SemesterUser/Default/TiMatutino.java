package com.gestaotech.api.dto.SemesterUser.Default;

import lombok.Data;

import java.util.List;

@Data
public class TiMatutino {
    private List<List<String>> list= List.of(
            List.of("IMD0017", "IMD0018", "IMD0020", "IMD1001", "IMD1002", "IMD1003", "IMD1004"),
            List.of("IMD0024", "IMD0027", "IMD0028", "IMD0034", "IMD1012"),
            List.of("IMD0029", "IMD0030", "IMD0033", "IMD0038", "IMD0121"),
            List.of("IMD0036", "IMD0039", "IMD0040"),
            List.of("IMD0043"),
            List.of("IMD0401")
    );
}
