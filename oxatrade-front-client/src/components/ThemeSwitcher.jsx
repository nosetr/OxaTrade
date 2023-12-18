/**
 * We are using a dropdown component that is available in Bootstrap
 * to implement our theme switcher. We are also using the built-in
 * button classes to set the size and color of the dropdown button.
 * @see https://blog.logrocket.com/using-bootstrap-react-tutorial-examples/
 */
import { useState } from "react";
import {
  Dropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";

const Theme = () => {
  const [theme, setTheme] = useState(null);
  const [dropdownOpen, setdropdownOpen] = useState(false);
  const resetTheme = () => {
    setTheme(null);
  };

  return (
    <div className="mb-2">
      <div
        className={`text-capitalize h1 mb-4 w-100 text-center text-${
          theme ? theme.toLowerCase() : "secondary"
        }`}
      >
        {`${theme || "Default"}`}
      </div>
      <div className="d-flex justify-content-center p-5">
        <Dropdown
          isOpen={dropdownOpen}
          toggle={() => setdropdownOpen(!dropdownOpen)}
        >
          <DropdownToggle color={theme} caret>
            Dropdown
          </DropdownToggle>
          <DropdownMenu>
            <DropdownItem onClick={() => setTheme("primary")}>
              Primary Theme
            </DropdownItem>
            <DropdownItem onClick={() => setTheme("danger")}>
              Danger Theme
            </DropdownItem>
            <DropdownItem onClick={() => setTheme("success")}>
              Success Theme
            </DropdownItem>
            <DropdownItem divider />
            <DropdownItem onClick={resetTheme}>Default Theme</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </div>
    </div>
  );
};

export default Theme;

