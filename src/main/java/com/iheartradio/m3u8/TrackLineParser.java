package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.TrackData;

class TrackLineParser implements LineParser {
    @Override
    public void parse(String line, ParseState state) throws ParseException {
        final TrackData.Builder builder = new TrackData.Builder();
        final MediaParseState mediaState = state.getMedia();

        if (state.isExtended() && mediaState.trackInfo == null) {
            throw ParseException.create(ParseExceptionType.MISSING_TRACK_INFO, line);
        }

        mediaState.tracks.add(builder
                .withUri(line)
                .withTrackInfo(mediaState.trackInfo)
                .withEncryptionData(mediaState.encryptionData)
                .isDiscontinutyExists(mediaState.discontinutyExists)
                .build());

        mediaState.trackInfo = null;
    }
}
