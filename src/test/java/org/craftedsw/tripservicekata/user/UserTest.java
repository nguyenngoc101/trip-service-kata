package org.craftedsw.tripservicekata.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class UserTest {

  private static final User BOB = new User();
  private static final User PAUL = new User();


  @Test
  public void shouldInformWhenUsersAreNotFriends() {

    User user = UserBuilder.aUser()
        .friendsWith(BOB)
        .build();

    assertThat(user.isFriendsWith(PAUL), is(false));
  }

  @Test
  public void shouldInformWhenUserAreFriends() {
    User user = UserBuilder.aUser()
        .friendsWith(PAUL)
        .build();
    assertThat(user.isFriendsWith(PAUL), is(true));
  }
}
