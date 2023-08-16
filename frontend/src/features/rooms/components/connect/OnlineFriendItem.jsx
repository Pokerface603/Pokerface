import Button from "@component/Button";
import { participateRoom } from "@feature/rooms/api/session";
import { hashOpenviduTitle } from "@util/hashing";
import React from "react";
import { useNavigate } from "react-router";

function OnlineFriendItem({ friendNickname, roomInfoRes }) {
  const navigate = useNavigate();

  const onClickPlayTogether = async () => {
    const { title, gameMode } = roomInfoRes;

    const sessionId = hashOpenviduTitle(title);

    if (gameMode !== "BLIND") {
      const token = await participateRoom(sessionId, "");
      navigate(`../game/${sessionId}`, { state: { token, gameMode } });
    } else {
      navigate(`../game/${sessionId}`, { state: { gameMode } });
    }
  };

  return (
    <>
      <div className="m-auto">{/* <TierNoBoxSvg tier={tier} /> */}</div>
      <p
        className="col-span-3"
        style={{ fontFamily: "NexonGothic", fontSize: "30px" }}
      >
        {friendNickname}
      </p>
      <div className="col-end-7 col-span-2">
        {roomInfoRes && (
          <Button
            width="130px"
            height="30px"
            label="같이하기"
            background="var(--brown_dark)"
            color="white"
            onClick={onClickPlayTogether}
          />
        )}
      </div>
    </>
  );
}

export default OnlineFriendItem;
