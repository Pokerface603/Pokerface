import React from "react";
import TierNoBoxSvg from "../constants/TierNoBoxSvg";
import Button from "@component/Button";

function OnlineMemberItem({ tier, nickname }) {
  return (
    <>
      <div className="m-auto">{/* <TierNoBoxSvg tier={tier} /> */}</div>
      <p
        className="col-span-3"
        style={{ fontFamily: "NexonGothic", fontSize: "30px" }}
      >
        {nickname}
      </p>
      <div className="col-end-7 col-span-2">
        <Button
          width="130px"
          height="30px"
          label="친구맺기"
          background="var(--brown_dark)"
          color="white"
        />
      </div>
    </>
  );
}

export default OnlineMemberItem;
