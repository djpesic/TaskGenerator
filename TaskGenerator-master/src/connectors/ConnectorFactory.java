/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

/**
 *
 * @author djordje
 */
public class ConnectorFactory {

    public static LoopConnector getSequencerConnector() {
        return new SimpleSequencer();
    }

    public static LoopConnector getNesterConnector() {
        return new SimpleNester();
    }

}
