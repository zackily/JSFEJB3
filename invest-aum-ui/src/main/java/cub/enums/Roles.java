/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.enums;

/**
 *
 * @author NT48810
 */
public enum Roles {
    分行經辦(1),
    分行主管(2),
    信託部經辦(3),
    信託部主管(4),
    前後台經辦(5),
    前後台主管(6),
    安控管理員(7),
    系統管理員(8),
    其他(9),
    ARM(11);

    private int value;

    private Roles(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static String getName(int index) {
        for (Roles c : Roles.values()) {
            if (c.getValue() == index) {
                return c.name();
            }
        }
        return null;
    }
}
