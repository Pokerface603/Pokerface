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

function RoomCard({
  title,
  hostName,
  hostTier,
  playerCount,
  gameMode,
  isPrivate,
}) {
  const [showPrivateModal, setShowPrivateModal] = useState(false);

  const navigate = useNavigate();

  const enterGameRoom = async () => {
    if (isPrivate) {
      setShowPrivateModal(true);
      return;
    }

    const sessionId = hashOpenviduTitle(title);
    if (gameMode !== "BLIND") {
      const token = await participateRoom(sessionId, "");
      navigate(`../game/${sessionId}`, { state: { token, gameMode } });
    } else {
      navigate(`../game/${sessionId}`, { state: { gameMode } });
    }
  };

  const onCloseModal = () => {
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
