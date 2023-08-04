import Input from "@component/Input";
import CheckIcon from "./CheckIcon";

import {
  height,
  nameWidth,
  pwWidth,
  checkWidth,
  idProps,
  nicknameProps,
  passwordProps,
  passCheckProps,
} from "./registFormStyle";

const RegistForm = () => {
  return (
    <div
      className="grid grid-cols-1 justify-items-center content-center"
      style={{ width: "912px", height: "333px" }}>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={idProps.type}
          placeholder={idProps.placeholder}
        />
        <CheckIcon width={checkWidth} height={height} />
      </div>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={nicknameProps.type}
          placeholder={nicknameProps.placeholder}
        />
        <CheckIcon width={checkWidth} height={height} />
      </div>
      <Input
        width={pwWidth}
        height={height}
        type={passwordProps.type}
        placeholder={passwordProps.placeholder}
      />
      <Input
        width={pwWidth}
        height={height}
        type={passCheckProps.type}
        placeholder={passCheckProps.placeholder}
      />
    </div>
  );
};

export default RegistForm;
