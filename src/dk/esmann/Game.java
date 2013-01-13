package dk.esmann;

import android.os.Parcel;
import android.os.Parcelable;

class Game implements Parcelable{
    private int id, numberOfRounds, hours, minutes;
    private String name;

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game(int id, String name, int numberOfRounds, int hours, int minutes) {
        this.id = id;
        this.name = name;
        this.numberOfRounds = numberOfRounds;
        this.hours = hours;
        this.minutes = minutes;

    }

    @Override
    public String toString() {
        return name;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(numberOfRounds);
        parcel.writeInt(hours);
        parcel.writeInt(minutes);
    }

    @SuppressWarnings("UnusedDeclaration")
    public static final Parcelable.Creator<Game> CREATOR = new Creator<Game>() {
        public Game createFromParcel(Parcel parcel) {
            return new Game(parcel);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    private Game(Parcel parcel) {
        id = parcel.readInt();
        name = parcel.readString();
        numberOfRounds = parcel.readInt();
        hours = parcel.readInt();
        minutes = parcel.readInt();
    }

}
