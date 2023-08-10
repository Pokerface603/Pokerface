import React, { createRef, useEffect } from "react";

function OpeviduVideo({ streamManager }) {
  const videoRef = createRef();

  useEffect(() => {
    streamManager.addVideoElement(videoRef.current);
  }, [streamManager, videoRef]);

  return (
    streamManager && (
      <video
        style={{
          width: "400px",
          position: "absolute",
          left: "calc(50vw - 200px)",
          top: "0px",
        }}
        autoPlay={true}
        ref={videoRef}
      />
    )
  );
}

export default OpeviduVideo;
