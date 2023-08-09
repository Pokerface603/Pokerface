import React from "react";
import TabItem from "./TabItem";

const Tab = ({ onClickTab }) => {
  return (
    <>
      {/* Tab은 수정이 필요함. 뭐가 연결된건지 전혀 모르겠음. */}
      <ul className="flex list-none flex-row flex-wrap pl-0">
        <TabItem mode={"NORMAL"} onClick={onClickTab} />
        <TabItem mode={"BLIND"} onClick={onClickTab} />
        <TabItem mode={"EMOTION"} onClick={onClickTab} />
      </ul>
    </>
  );
};

export default Tab;
