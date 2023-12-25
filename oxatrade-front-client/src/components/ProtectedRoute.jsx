/**
 * The <ProtectedRoute /> component will simply check the current user
 * state from the useAuth hook and then redirect to the Home screen if
 * the user is not authenticated:
 */
import { Navigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

export const ProtectedRoute = ({ children }) => {
  const { user } = useAuth();
  if (!user) {
    // user is not authenticated
    return <Navigate to="/" />;
  }
  return children;
};