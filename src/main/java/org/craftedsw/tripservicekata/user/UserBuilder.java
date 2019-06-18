package org.craftedsw.tripservicekata.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.craftedsw.tripservicekata.trip.Trip;

public class UserBuilder {

  private List<User> friends = new ArrayList<>();
  private List<Trip> trips = new ArrayList<>();

  public static UserBuilder aUser() {
    return new UserBuilder();
  }

  public UserBuilder friendsWith(User... friends) {
    this.friends.addAll(Arrays.asList(friends));
    return this;
  }

  public UserBuilder trips(Trip... tripList) {
    trips.addAll(Arrays.asList(tripList));
    return this;
  }

  public User build() {
    return new User(this);
  }

  public List<User> getFriends() {
    return friends;
  }

  public void setFriends(List<User> friends) {
    this.friends = friends;
  }

  public List<Trip> getTrips() {
    return trips;
  }

  public void setTrips(List<Trip> trips) {
    this.trips = trips;
  }
}
