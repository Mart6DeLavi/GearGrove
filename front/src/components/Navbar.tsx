import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const Navbar: React.FC = () => {
    const location = useLocation();

    return (
        <header className="bg-gradient-to-r from-purple-600 to-indigo-600 text-white shadow-lg">
            <nav className="container mx-auto px-6 py-4">
                <div className="flex justify-between items-center">
                    <Link to="/" className="text-2xl font-bold">
                        GearGrove
                    </Link>
                    <div className="flex space-x-6">
                        <NavLink to="/" current={location.pathname}>Home</NavLink>
                        <NavLink to="/profile" current={location.pathname}>Profile</NavLink>
                        <NavLink to="/orders" current={location.pathname}>Orders</NavLink>
                        <NavLink to="/contact" current={location.pathname}>Contact</NavLink>
                    </div>
                </div>
            </nav>
        </header>
    );
};

const NavLink: React.FC<{ to: string; current: string; children: React.ReactNode }> = ({ to, current, children }) => (
    <Link
        to={to}
        className={`text-white hover:text-gray-200 transition duration-150 ease-in-out ${
            current === to ? 'border-b-2 border-white' : ''
        }`}
    >
        {children}
    </Link>
);

export default Navbar;