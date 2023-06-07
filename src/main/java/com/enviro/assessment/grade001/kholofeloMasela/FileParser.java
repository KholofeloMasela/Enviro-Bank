package com.enviro.assessment.grade001.kholofeloMasela;

import java.io.File;
import java.net.URI;

public interface FileParser {
    void parserCSV(File csv);
    File convertCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);


}
