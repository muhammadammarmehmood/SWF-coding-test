package com.smallworld.data;

public class Transaction {
    // Represent your transaction data here.
    private float mtn;
    private float amount;
    private String senderFullName;
    private float senderAge;
    private String beneficiaryFullName;
    private float beneficiaryAge;
    private float issueId;
    private boolean issueSolved;
    private String issueMessage;


    // Getter Methods

    public float getMtn() {
        return mtn;
    }

    public float getAmount() {
        return amount;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public float getSenderAge() {
        return senderAge;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public float getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public float getIssueId() {
        return issueId;
    }

    public boolean getIssueSolved() {
        return issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    // Setter Methods

    public void setMtn( float mtn ) {
        this.mtn = mtn;
    }

    public void setAmount( float amount ) {
        this.amount = amount;
    }

    public void setSenderFullName( String senderFullName ) {
        this.senderFullName = senderFullName;
    }

    public void setSenderAge( float senderAge ) {
        this.senderAge = senderAge;
    }

    public void setBeneficiaryFullName( String beneficiaryFullName ) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public void setBeneficiaryAge( float beneficiaryAge ) {
        this.beneficiaryAge = beneficiaryAge;
    }

    public void setIssueId( float issueId ) {
        this.issueId = issueId;
    }

    public void setIssueSolved( boolean issueSolved ) {
        this.issueSolved = issueSolved;
    }

    public void setIssueMessage( String issueMessage ) {
        this.issueMessage = issueMessage;
    }
}
