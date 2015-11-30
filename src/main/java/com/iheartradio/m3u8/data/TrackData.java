package com.iheartradio.m3u8.data;

import java.util.Objects;

public class TrackData {
    private final String mUri;
    private final TrackInfo mTrackInfo;
    private final EncryptionData mEncryptionData;
    private final boolean mDiscontinutyExists;

  // private enum DiscontinuityPosition {
  // BEFORE, AFTER, BOTH
  // }

  // public DiscontinuityPosition discontinuityPosition;

    private TrackData(String uri, TrackInfo trackInfo, EncryptionData encryptionData, boolean discontinutyExists) {
    	mUri = uri;
    	mTrackInfo = trackInfo;
    	mEncryptionData = encryptionData;
    	mDiscontinutyExists = discontinutyExists;
    }


    public String getUri() {
        return mUri;
    }

    public boolean hasTrackInfo() {
        return mTrackInfo != null;
    }

    public TrackInfo getTrackInfo() {
        return mTrackInfo;
    }

    public boolean hasEncryptionData() {
        return mEncryptionData != null;
    }

    public boolean isEncrypted() {
        return hasEncryptionData() &&
               mEncryptionData.getMethod() != null &&
               mEncryptionData.getMethod() != EncryptionMethod.NONE;
    }
    
    public boolean isDiscontinutyExists() {
      return mDiscontinutyExists;
    }
    
    public EncryptionData getEncryptionData() {
        return mEncryptionData;
    }

    public Builder buildUpon() {
      return new Builder(getUri(), mTrackInfo, mEncryptionData, mDiscontinutyExists);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(mUri, mEncryptionData, mTrackInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TrackData)) {
            return false;
        }

        TrackData other = (TrackData) o;
        
        return Objects.equals(mUri, other.getUri()) &&
               Objects.equals(this.mEncryptionData, other.mEncryptionData) &&
               Objects.equals(this.mTrackInfo, other.mTrackInfo);
    }

    @Override
    public String toString() {
      return "TrackData [mUri=" + mUri + ", mTrackInfo=" + mTrackInfo + ", mEncryptionData=" + mEncryptionData + ", mDiscontinutyExists="
          + mDiscontinutyExists + "]";
    }
    
    
    public static class Builder {
        private String mUri;
        private TrackInfo mTrackInfo;
        private EncryptionData mEncryptionData;
        private boolean mDiscontinutyExists;

        public Builder() {
        }

        private Builder(String uri, TrackInfo trackInfo, EncryptionData encryptionData, boolean discontinutyExists) {
            mUri = uri;
            mTrackInfo = trackInfo;
            mEncryptionData = encryptionData;
            mDiscontinutyExists = discontinutyExists;
        }

        public Builder withUri(String url) {
            mUri = url;
            return this;
        }

        public Builder withTrackInfo(TrackInfo trackInfo) {
            mTrackInfo = trackInfo;
            return this;
        }

        public Builder withEncryptionData(EncryptionData encryptionData) {
            mEncryptionData = encryptionData;
            return this;
        }
        
        public Builder isDiscontinutyExists(boolean  discontinutyExists) {
          mDiscontinutyExists = discontinutyExists;
          return this;
        }

        public TrackData build() {
          return new TrackData(mUri, mTrackInfo, mEncryptionData, mDiscontinutyExists);
        }
    }
}
