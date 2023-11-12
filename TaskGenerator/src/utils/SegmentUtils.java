/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import programSegment.InPortType;
import programSegment.LoopType;
import programSegment.OutPortType;
import programSegment.Segment;

/**
 *
 * @author djordje
 */
public class SegmentUtils {

    public static InPortType getInPortType(Segment s) {
        if (s.getProgram().getConst() != null) {
            return s.getProgram().getConst().getInPorts();
        }
        if (s.getProgram().getLoop() != null) {
            LoopType lt = s.getProgram().getLoop();
            if (lt.getSimpleLoop() != null) {
                if (lt.getSimpleLoop().getFor() != null) {
                    return lt.getSimpleLoop().getFor().getInPorts();
                }
                if (lt.getSimpleLoop().getWhile() != null) {
                    return lt.getSimpleLoop().getWhile().getPorts().getInPorts();
                }
                if (lt.getSimpleLoop().getRepeat() != null) {
                    return lt.getSimpleLoop().getRepeat().getPorts().getInPorts();
                }
            }
        }
        return null;
    }

    public static OutPortType getOutPortType(Segment s) {
        if (s.getProgram().getConst() != null) {
            return s.getProgram().getConst().getOutPorts();
        }
        if (s.getProgram().getLoop() != null) {
            LoopType lt = s.getProgram().getLoop();
            if (lt.getSimpleLoop() != null) {
                if (lt.getSimpleLoop().getFor() != null) {
                    return lt.getSimpleLoop().getFor().getOutPorts();
                }
                if (lt.getSimpleLoop().getWhile() != null) {
                    return lt.getSimpleLoop().getWhile().getPorts().getOutPorts();
                }
                if (lt.getSimpleLoop().getRepeat() != null) {
                    return lt.getSimpleLoop().getRepeat().getPorts().getOutPorts();
                }
            }
        }
        return null;
    }
}
