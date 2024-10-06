package com.verysafe.falconshield.devices.domain.model.values;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class DeviceSpecs {
    private String power;
    private String connectivity;
    private String compatibility;
    private String warranty;
}
