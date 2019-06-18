package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

  @Autowired
  private TripDAO tripDAO;

  public List<Trip> getFriendTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
    valiadate(loggedInUser);

    return friend.isFriendsWith(loggedInUser)
        ? tripsBy(friend)
        : noTrips();
  }

  protected void valiadate(User loggedInUser) {
    if (loggedInUser == null) {
      throw new UserNotLoggedInException();
    }
  }

  protected List<Trip> noTrips() {
    return Collections.emptyList();
  }

  protected List<Trip> tripsBy(User user) {
    return tripDAO.tripsBy(user);
  }
}
