import Button from "@component/Button";
import TierBox from "@component/TierBox";
import React, { useState } from "react";
import Dividor from "./Dividor";
import { hashOpenviduTitle } from "@util/hashing";
import { useNavigate } from "react-router-dom";
import { participateRoom } from "@feature/rooms/api/session";
import { roomColor } from "../constants/RoomColor";
import Lock from "@asset/images/Lock.png";
import PrivateRoomInputModal from "../PrivateRoomInputModal/PrivateRoomInputModal";
import soundEffects from "@config/soundEffects";

function RoomCard({
  title,
  hostName,
  hostTier,
  playerCount,
  gameMode,
  isPrivate,
  fetchRoomData,
}) {
  const [showPrivateModal, setShowPrivateModal] = useState(false);

  const navigate = useNavigate();

  const enterGameRoom = async () => {
    soundEffects.shot.play();
    if (isPrivate) {
      setShowPrivateModal(true);
      return;
    }

    const sessionId = hashOpenviduTitle(title);

    try {
      const token = await participateRoom(sessionId, "");
      navigate(`../game/${sessionId}`, { state: { token, gameMode } });
    } catch (err) {
      await fetchRoomData(1);
    }
  };

  const onCloseModal = () => {
    soundEffects.load.play();
    setShowPrivateModal(false);
  };

  return (
    <>
      <div
        style={{
          display: "inline-block",
          border: `6px solid ${roomColor[hostTier]}`,
          borderRadius: "18px",
        }}
      >
        <div
          style={{
            width: "580px",
            height: "130px",
            padding: "12px 15px",
            gap: "3px",
          }}
          className="bg-room box-border bg-cover flex items-center"
        >
          <TierBox tier={hostTier} />
          <div
            className="w-full flex flex-col justify-evenly h-full font-nexonGothic"
            style={{
              fontSize: "25px",
              fontWeight: "bold",
            }}
          >
            <div
              className="flex justify-between items-center"
              style={{
                marginRight: "135px",
              }}
            >
              <span>{title}</span>
              <span
                style={{
                  fontFamily: "Unchained-Spaghetti",
                  fontSize: "30px",
                }}
              >
                {playerCount} / 2
              </span>
            </div>
            <Dividor />
            <div className="flex justify-between items-center">
              <span>{hostName}</span>
              <Button
                label={"입장"}
                background={"var(--orange)"}
                width={"100px"}
                color={"white"}
                height={"33px"}
                fontSize={"20px"}
                onClick={enterGameRoom}
                icon={isPrivate ? <img src={Lock} alt="자물쇠" /> : <></>}
              />
            </div>
          </div>
        </div>
      </div>
      {showPrivateModal && (
        <PrivateRoomInputModal
          close={onCloseModal}
          roomName={title}
          gameMode={gameMode}
        />
      )}
    </>
  );
}

export default RoomCard;
