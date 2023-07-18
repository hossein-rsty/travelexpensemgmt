package com.travelexpensemgmt.docservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Doc {
    private  final String docId;
    private String name;
    private DocType docType;
    private URL url;
}
