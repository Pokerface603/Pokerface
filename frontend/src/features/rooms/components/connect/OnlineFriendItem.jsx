import Button from "@component/Button";
import React from "react";

function OnlineFriendItem({ friendNickname, roomInfoRes }) {
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
          />
        )}
      </div>
    </>
  );
}

export default OnlineFriendItem;
