const Input = ({
  type,
  onChange,
  value,
  placeholder,
  id,
  width,
  height,
  style,
  onKeyDown,
}) => {
  return (
    <input
      type={type}
      onChange={onChange}
      value={value}
      placeholder={placeholder}
      id={id}
      onKeyDown={onKeyDown}
      aria-describedby="input box"
      required
      className="bg-transparent block placeholder-black/[0.5] border-solid border-black/[0.6] border-b-2 text-black focus:outline-none focus:bg-gray-50/20 p-2.5 mt-2 mr-2 ml-2"
      style={{
        width,
        height,
        fontFamily: "Unchained-Spaghetti",
        fontSize: "40px",
        ...style,
      }}
    />
  );
};

export default Input;
