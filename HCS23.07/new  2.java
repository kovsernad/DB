 public void patientHisoryInfo(Vector<PatientInfo> patient, String toMatch, JTextPane[] patientTP){

               String ghstr, ilstr, spstr;
                int id = 0;
                StringBuffer infogh = new StringBuffer();
                StringBuffer infoil = new StringBuffer();
                StringBuffer infosp = new StringBuffer();

            int ind = -1;
            String lName = toMatch.substring(0, toMatch.indexOf(","));
            String dBirth = toMatch.substring(toMatch.indexOf("(") + 1, toMatch.indexOf(")"));
            for (int i = 0; i < patient.size(); i++) {
                if (patient.get(i).getLname().equalsIgnoreCase(lName) && patient.get(i).getBdate().toString().equalsIgnoreCase(dBirth)) {
                    ind = i;
                }
            }

            for (int i = 0; i< patient.size(); i++){
                
                ghstr = patient.get(i).getGmedhistory();
                
                if(ghstr != null) {
                String[] ghparts = ghstr.split("[|]");
                
                        for( i=0; i< ghparts.length; i++){
                            System.out.println("1111"+ghparts[i]);
                                if ( i%2 != 0){
                                    ghstr =  "<span style='font-size: 16pt'>"
                                                +ghparts[i]+
                                                "</span><br /><br />";
                                    infogh.append(ghstr);
                                }
                                else if ( i%2 == 0){
                                    ghstr =  "<b><span style='font-size: 16pt'>"
                                                +ghparts[i]+
                                                "</span></b><br /><br />";
                                    infogh.append(ghstr);
                                }
                            patientTP[0].setText(infogh.toString());
                            }     
                        }
                
                ilstr = patient.get(i).getIllnesshistory();
                
                if(ilstr != null) {
                String[] ilparts = ilstr.split("[|]");
                
                        for( i=0; i< ilparts.length; i++){
                                if ( i%2 != 0){
                                    ilstr =  "<span style='font-size: 16pt'>"
                                                +ilparts[i]+
                                                "</span><br /><br />";
                                    infoil.append(ghstr);
                                }
                                else if ( i%2 == 0){
                                    ilstr =  "<b><span style='font-size: 16pt'>"
                                                +ilparts[i]+
                                                "</span></b><br /><br />";
                                    infoil.append(ilstr);
                                }
                            }
                                        patientTP[1].setText(infoil.toString());
                        }

                spstr = patient.get(i).getMedspechistory();
                
                if(spstr != null) {
                String[] spparts = spstr.split("[|]");
                
                        for( i=0; i< spparts.length; i++){
                                if ( i%2 != 0){
                                    spstr =  "<span style='font-size: 16pt'>"
                                                +spparts[i]+
                                                "</span><br /><br />";
                                    infosp.append(ghstr);
                                }
                                else if ( i%2 == 0){
                                    spstr =  "<b><span style='font-size: 16pt'>"
                                                +spparts[i]+
                                                "</span></b><br /><br />";
                                    infosp.append(spstr);
                                }
                            }

                                         patientTP[2].setText(infosp.toString());
                }  
            }                            
    }