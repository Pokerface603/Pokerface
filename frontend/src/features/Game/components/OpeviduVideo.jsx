import React, { createRef, useEffect, useRef, useState } from "react";
import * as faceapi from "face-api.js";
import Parchment from "@component/Parchment";

const SSD_MOBILENETV1 = "ssd_mobilenetv1";
const TINY_FACE_DETECTOR = "tiny_face_detector";

let selectedFaceDetector = TINY_FACE_DETECTOR;

function getCurrentFaceDetectionNet() {
  if (selectedFaceDetector === SSD_MOBILENETV1) {
    return faceapi.nets.ssdMobilenetv1;
  }
  if (selectedFaceDetector === TINY_FACE_DETECTOR) {
    return faceapi.nets.tinyFaceDetector;
  }
}

function isFaceDetectionModelLoaded() {
  return !!getCurrentFaceDetectionNet().params;
}

// ssd_mobilenetv1 options
let minConfidence = 0.5;

// tiny_face_detector options
let inputSize = 512;
let scoreThreshold = 0.5;

function getFaceDetectorOptions() {
  return selectedFaceDetector === SSD_MOBILENETV1
    ? new faceapi.SsdMobilenetv1Options({ minConfidence })
    : new faceapi.TinyFaceDetectorOptions({ inputSize, scoreThreshold });
}

function OpeviduVideo({ streamManager, gameMode, startEmotion }) {
  const videoRef = useRef();
  const spanRef = useRef();

  const [text, setText] = useState("");

  useEffect(() => {
    streamManager.addVideoElement(videoRef.current);
  }, [streamManager, videoRef]);

  useEffect(() => {
    const videoEl = videoRef.current;

    const loadModels = async () => {
      const MODEL_URL = process.env.PUBLIC_URL + "/model";
      Promise.all([
        faceapi.nets.tinyFaceDetector.loadFromUri(MODEL_URL),
        faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
        faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
        faceapi.nets.faceExpressionNet.loadFromUri(MODEL_URL),
      ]);
    };

    loadModels();
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then(function (stream) {
        videoEl.srcObject = stream;
      })
      .catch(function (err) {
        console.log(err);
      });
  }, []);

  const onPlay = async () => {
    const videoEl = videoRef.current;
    if (
      !videoEl ||
      videoEl.paused ||
      videoEl.ended ||
      !isFaceDetectionModelLoaded()
    )
      return setTimeout(() => onPlay());

    const options = getFaceDetectorOptions();

    const data = await faceapi
      .detectSingleFace(videoEl, options)
      .withFaceExpressions();

    if (!data) {
      return setTimeout(() => onPlay());
    }

    const { expressions } = data;

    console.log(expressions);

    const most = Object.keys(expressions).reduce((ex1, ex2) => {
      return expressions[ex1] > expressions[ex2] ? ex1 : ex2;
    });

    spanRef.current.innerText = most;
    // setText(most);
    setTimeout(() => onPlay(), 500);
  };

  useEffect(() => {
    if (startEmotion) {
      onPlay();
    }
  }, [startEmotion]);

  return (
    streamManager &&
    (gameMode === "EMOTION" ? (
      <>
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

        <Parchment
          style={{
            position: "absolute",
            left: "75vw",
            top: "0px",
            width: "200px",
            height: "200px",
            backgroundColor: "black",
            fontFamily: "NexonGothic",
            fontSize: "24px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "column",
          }}
          className="flex justify-center items-center"
        >
          <div>상대방의 감정</div>
          <div ref={spanRef}>{text}</div>
        </Parchment>
      </>
    ) : (
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
    ))
  );
}

export default OpeviduVideo;
