package org.craftedsw.tripservicekata.trip;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

  private static final User GUEST = null;
  private static final User UNUSED_USER = null;
  private static final User ANOTHER_USER = new User();
  private static final Trip TO_BRAZIL = new Trip();
  private static final User REGISTERED_USER = new User();
  private static final Trip TO_LONDON = new Trip();

  @Mock
  private TripDAO tripDAO;

  @InjectMocks
  @Spy
  private TripService tripService = new TripService();

  @Test(expected = UserNotLoggedInException.class)
  public void shouldThrowExceptionWhenUserIsNotLogged() {
    tripService.getFriendTrips(UNUSED_USER, GUEST);
  }

  @Test
  public void shouldNotReturnAnyTripsWhenUsersAreNotFriends() {
    User friend = UserBuilder.aUser()
        .friendsWith(ANOTHER_USER)
        .trips(TO_BRAZIL)
        .build();

    List<Trip> trips = tripService.getFriendTrips(friend, REGISTERED_USER);
    assertThat(trips.size(), is(0));
  }

  @Test
  public void shouldReturnTripsWhenUserAreFriends() {
    User friend = UserBuilder.aUser()
        .friendsWith(ANOTHER_USER, REGISTERED_USER)
        .trips(TO_BRAZIL, TO_LONDON)
        .build();

    given(tripDAO.tripsBy(friend)).willReturn(friend.trips());
    List<Trip> trips = tripService.getFriendTrips(friend, REGISTERED_USER);

    assertThat(trips.size(), is(2));
  }
}
