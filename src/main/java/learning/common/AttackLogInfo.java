package learning.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.avro.reflect.Nullable;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;

import java.util.Objects;

@Getter
@Setter
@Builder
@DefaultCoder(AvroCoder.class)

public class AttackLogInfo {

    @Nullable
    private String time;
    @Nullable
    private String map;
    @Nullable
    private String mapPoint;
    @Nullable
    private String state;
    @Nullable
    private String result;
    @Nullable
    private String enemyName;
    @Nullable
    private String drop;
    @Nullable
    private String damagedShip;
    @Nullable
    private String flagShip;
    @Nullable
    private String flagShipTsu;
    @Nullable
    private String mvpShip;
    @Nullable
    private String mvpShipTsu;

    public AttackLogInfo(String time, String map, String mapPoint, String state, String result, String enemyName, String drop, String damagedShip, String flagShip, String flagShipTsu, String mvpShip, String mvpShipTsu) {
        this.time = time;
        this.map = map;
        this.mapPoint = mapPoint;
        this.state = state;
        this.result = result;
        this.enemyName = enemyName;
        this.drop = drop;
        this.damagedShip = damagedShip;
        this.flagShip = flagShip;
        this.flagShipTsu = flagShipTsu;
        this.mvpShip = mvpShip;
        this.mvpShipTsu = mvpShipTsu;
    }

    public AttackLogInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttackLogInfo that = (AttackLogInfo) o;
        return time.equals(that.time) &&
                map.equals(that.map) &&
                mapPoint.equals(that.mapPoint) &&
                state.equals(that.state) &&
                result.equals(that.result) &&
                enemyName.equals(that.enemyName) &&
                Objects.equals(drop, that.drop) &&
                Objects.equals(damagedShip, that.damagedShip) &&
                flagShip.equals(that.flagShip) &&
                Objects.equals(flagShipTsu, that.flagShipTsu) &&
                mvpShip.equals(that.mvpShip) &&
                Objects.equals(mvpShipTsu, that.mvpShipTsu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, map, mapPoint, state, result, enemyName, drop, damagedShip, flagShip, flagShipTsu, mvpShip, mvpShipTsu);
    }
}
