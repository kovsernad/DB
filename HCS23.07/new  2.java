 public void staffListInfo(Vector<StaffInfo> staff, Object comp, JTextField tf, DefaultListModel list) {
        JTextPane cp = (JTextPane) comp;
        list.removeAllElements();
        StringBuffer st = new StringBuffer();
        Vector<StringBuffer> t = new Vector();
        st.append("<table width='250'>");
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getLname().toLowerCase().startsWith(
                    tf.getText().toLowerCase())) {
                // preparing information for staff info
                st.append("<tr><td><b>");
                st.append(staff.get(i).getLname());
                st.append(", " + staff.get(i).getFname());
                st.append("</b></td>");
                st.append("<td><b>");
                st.append(staff.get(i).getbDate().toString());
                st.append("</b></td>");
                st.append("</tr>");
                // preparing information for list
                StringBuffer tmp = new StringBuffer();
                tmp.append(staff.get(i).getLname() + ", ");
                tmp.append(staff.get(i).getFname().charAt(0) + ". (");
                tmp.append(staff.get(i).getbDate().toString() + ")");
                t.add(tmp);
            }
        }
        //finishing and sending information to staff info
        st.append("</table>");
        cp.setText(st.toString());
        // building list
        for (int i = 0; i < t.size(); i++) {
            list.add(i, t.get(i).toString());
        }
    }