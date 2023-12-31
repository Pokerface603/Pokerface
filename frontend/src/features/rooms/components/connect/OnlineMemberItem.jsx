import React, { useContext } from "react";
import Button from "@component/Button";
import { WebSocketContext } from "context/WebsocketProvider";
import TierBox from "@component/TierBox";

function OnlineMemberItem({ tier, nickname, email, isFriend }) {
  const ws = useContext(WebSocketContext);

  const onClickRequestFriend = () => {
    ws.current.send(`REQUEST,${email}`);
  };
  return (
    <>
      <div className="m-auto"><TierBox tier={tier} /></div>
      <p
        className="col-span-3"
        style={{ fontFamily: "NexonGothic", fontSize: "28px" }}
      >
        {nickname}
      </p>
      <div className="col-end-7 col-span-2">
        {!isFriend && (
          <Button
            width="130px"
            height="30px"
            label="친구맺기"
            background="var(--brown_dark)"
            color="white"
            onClick={onClickRequestFriend}
          />
        )}
      </div>
    </>
  );
}

export default OnlineMemberItem;
