/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Objects;
import programSegment.InPortType.Port;

/**
 *
 * @author djordje
 */
public class ProgramPort extends Port {

    public ProgramPort() {
        super();
        inherited = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        ProgramPort p = (ProgramPort) o;
        boolean result = name.equals(p.name);
        if (result) {
            return Objects.equals(inherited, p.inherited);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        if (inherited == null) {
            return name;
        }
        return "" + name + " " + inherited.toString();
    }
}
