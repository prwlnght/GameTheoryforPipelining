using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Speech.Recognition;

namespace SpeechRecognitionTesting
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        SpeechRecognitionEngine sre;
        public MainWindow()
        {
            InitializeComponent();
           

            // Register a handler for the SpeechRecognized event.
          //  sre.SpeechRecognized +=
            //  new EventHandler<SpeechRecognizedEventArgs>(sre_SpeechRecognized);

           

           

            // Start recognition.
            // sre.Recognize();
            //sre.SetInputToWaveFile(@"c:\Test\Colors.wav");

           




        }

        void speak_now_Click(Object sender, EventArgs e)
        {
            textBox.Text = "JHEre";

            sre = new SpeechRecognitionEngine();



            // sre.SetInputToWaveFile(@"c:\Test\Colors.wav");

            // Create a simple grammar that recognizes "red", "green", or "blue".
            Choices colors = new Choices();
            colors.Add(new string[] { "red", "green", "blue" });

            // Create a GrammarBuilder object and append the Choices object.
            GrammarBuilder gb = new GrammarBuilder();
            gb.Append(colors);

            // Create the Grammar instance and load it into the speech recognition engine.
            Grammar g = new Grammar(gb);
            sre.LoadGrammar(g);
            sre.EmulateRecognizeCompleted += Sre_EmulateRecognizeCompleted;

            sre.EmulateRecognize("red");
        }

        private void Sre_EmulateRecognizeCompleted(object sender, EmulateRecognizeCompletedEventArgs e)
        {
            MessageBox.Show("Speech recognized: " + e.Result.Text);
        }

        void sre_SpeechRecognized(object sender, SpeechRecognizedEventArgs e)
        {
           
        }
    }
}
