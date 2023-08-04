import React, { createRef, useEffect } from "react";

function OpeviduVideo({ streamManager }) {
  const videoRef = createRef();

  useEffect(() => {
    streamManager.addVideoElement(videoRef.current);
  }, [streamManager, videoRef]);

  return (
    streamManager && (
      <video
        style={{ width: "500px", height: "500px" }}
        autoPlay={true}
        ref={videoRef}
      />
    )
  );
}

export default OpeviduVideo;
