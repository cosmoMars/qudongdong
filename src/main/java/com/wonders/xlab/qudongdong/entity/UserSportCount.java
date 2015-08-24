package com.wonders.xlab.qudongdong.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * Created by mars on 15/8/23.
 */
@Entity
@Table(name = "QDD_USER_SPORT_COUNT")
public class UserSportCount extends AbstractPersistable<Long> {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private User user;

    /**
     * 邀请数
     */
    private int invitationCount;

    /**
     * 被邀请数
     */
    private int byInvitationCount;

    /**
     * 成功数量
     */
    private int successCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getInvitationCount() {
        return invitationCount;
    }

    public void setInvitationCount(int invitationCount) {
        this.invitationCount = invitationCount;
    }

    public int getByInvitationCount() {
        return byInvitationCount;
    }

    public void setByInvitationCount(int byInvitationCount) {
        this.byInvitationCount = byInvitationCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }
}
