import Howler from "howler"; // Howler 라이브러리 import
import loadSound from "../assets/musics/load.wav"; // Load 효과음 추가
import backgroundMusic from "../assets/musics/western.mp3"; //BackGroundMusic 추가
import shotSound from "../assets/musics/shot.wav";

// 효과음 객체 생성
const soundEffects = {
  bgm: new Howler.Howl({
    src: [backgroundMusic],
    loop: true,
    volume: 0.3, // 볼륨 설정 (0에서 1 사이 값) <- 수정 필요
  }),
  load: new Howler.Howl({
    src: [loadSound],
    volume: 0.5,
  }),
  shot: new Howler.Howl({
    src: [shotSound],
    volume: 0.6,
  }),
};

export default soundEffects;
